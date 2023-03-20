import { Link } from "react-router-dom";
import './BeachPage.css';
import React, { useState, useEffect } from 'react';
// import { StarRating } from "./StarRating";
import { BeachHistory } from "../model/BeachHistory";
import ReactStars from 'react-stars';




export interface BeachData {
  id: number;
  beach_name: string;
  state_code: string;
  county: string;
  rating: number;
  beachlength_km: number;
  tier_rank: number;
}

export function Beach(props:{beach:BeachData}){
  const beach = new BeachHistory();
    beach.setBeach_name = props.beach.beach_name;
    beach.setId = props.beach.id;
    beach.setRating = 0;
    beach.setRating_date = new Date();
    beach.setUser = "Test";
  const [beachRating, setBeachRating] = useState(beach);
  
  const beachHistoryList: BeachHistory[] = [];
  for (let i = 0; i < 9; i++){
    const temp = new BeachHistory();
    temp.setBeach_name = "";
    temp.setId = 0;
    temp.setRating = 0;
    temp.setUser = "JJF";
    temp.setRating_date = new Date();

    beachHistoryList[i] = temp;    
  }
  const [beachHistory, setbeachHistory] = useState<BeachHistory[]>(beachHistoryList);
  

  
  const getBeachHistory = () => {
    fetch(`http://localhost:8080/beachappbackend/getBeachHistory?beachId=${props.beach.id}`,{method:'GET'})
         .then((response) => {
          if (!response.ok) {
            throw new Error(response.statusText)
          }
          return response.json() as Promise <typeof BeachHistory[]>})
          .then((beaches) => {
            if (beaches.length > 0){
              const historyList: BeachHistory[] = [];
              beaches.map(
                (beach) =>
                { 
                  historyList.push(Object.assign(new BeachHistory(), beach))
                }
                )
                setbeachHistory(historyList);
            } 
          }
        )  
         .catch((err) => {
            console.log(err.message);
         });
  }

  const handleRatingSubmit = (newRating:number) => {
    beach.setRating = newRating;

    fetch(`http://localhost:8080/beachappbackend/submitBeachRating`,{method:'POST',body: JSON.stringify(beach),headers:{'Content-Type': 'application/json'}})
         .then((response) => {
          if (!response.ok) {
            throw new Error(response.statusText)
          }
          response.json();
        })
         .then((data) => {
          // console.log(data);
          getBeachHistory();
         })
         .catch((err) => {
            console.log(err.message);
         });

  }

  useEffect(() => { 
    getBeachHistory();
    setBeachRating(beach);
    }, [props])
  
  const DisplayData=beachHistory.map(
    (info)=>{
        return(
            <tr>
                <td>{info.getUser}</td>
                <td>{info.getRating}</td>
                <td>{new Date(info.getRating_date).toLocaleDateString()}</td>
            </tr>
        )
    }
  )
  
    
  
  return(
  <div className="beach-parent">
    <div className="heading">
      <Link to={'/Homepage'}>HOME</Link>
    </div>
    <div className="name center column-aligned">
      <h1>Name: {props.beach.beach_name}</h1>
      <h2>Rating: {props.beach.rating.toFixed(1)}</h2>
      <div>
    <ReactStars count={5} onChange={handleRatingSubmit} size={24} color2={'#ffd700'} />
      </div>

    </div>
    <div className="info center">
      <div className="beach-info center">
        <h2>Last Cleaned on: </h2>
        <h2>{props.beach.county}, {props.beach.state_code}</h2>
        <h2>Beach Length: {props.beach.beachlength_km * 1000}m</h2>
      </div>
    </div>
    <div className="history table">
      <table width={"40%"}>
        <thead>
          <tr>
          <th>User</th>
          <th>Rating</th>
          <th>Date</th>
          </tr>
        </thead>
        <tbody>
          
            
            {DisplayData}
            
        </tbody>
      </table>
    </div>
  </div>
  );
  }