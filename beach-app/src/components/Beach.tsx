import { Link } from "react-router-dom";
import './BeachPage.css';
import React, { useState, useEffect } from 'react';


export interface BeachData {
  id: number;
  beach_name: string;
  state_code: string;
  county: string;
  rating: number;
  beachlength_km: number;
  tier_rank: number;
}

export interface beachHistory {
  id: number;
  beach_name: string;
  rating: number;
  date: Date;
  user: string}

export function Beach(props:{beach:BeachData}){

  const beachHistoryList: beachHistory[] = [];
  for (let i = 0; i < 9; i++){
    beachHistoryList[i] = {id:0, beach_name:"", rating:0, date:new Date("2022-03-25"), user:"John John Florence"};
  }
  const [beachHistory, setbeachHistory] = useState(beachHistoryList);
  
  
    const DisplayData=beachHistory.map(
      (info)=>{
          return(
              <tr>
                  <td>{info.user}</td>
                  <td>{info.rating}</td>
                  <td>{info.date.toLocaleDateString()}</td>
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
      <h2>Rating: {props.beach.rating}</h2>
    </div>
    <div className="info center">
      <div className="beach-info center">
        <h2>Last Cleaned on: </h2>
        <h2>{props.beach.county}, {props.beach.state_code}</h2>
        <h2>Beach Length: {props.beach.beachlength_km * 1000}m</h2>
      </div>
    </div>
    <div className="history center">
      <table >
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