import '../App.css';
import { SearchBar } from '../components/SearchBar';
import React, { useState, useEffect } from 'react';



function Homepage(props:{searchBarInput: any, handleSearchBarChange: any, handleSearchBarSubmit: any}) {

  const beachList: {id:0, beach_name:"", state_code:"", county:"", rating:0, beachlength_km:0, tier_rank:0}[] = [];
  for (let i = 0; i < 9; i++){
    beachList[i] = {id:0, beach_name:"", state_code:"", county:"", rating:0, beachlength_km:0, tier_rank:0};
  }
  const [beaches, setBeaches] = useState(beachList);

  const getRandomListOfBeaches = () => {
    fetch(`http://localhost:8080/beachappbackend/getRandomBeachInfo?numberOfBeaches=9`,{method:'GET'})
         .then((response) => {
          if (!response.ok) {
            throw new Error(response.statusText)
          }
          return response.json() as Promise<typeof beaches >
        })
         .then((data) => {
          if (data.length > 0){
            setBeaches(data);
          } 
         })
         .catch((err) => {
            console.log(err.message);
         });
        }

        
        useEffect(() => { 
          getRandomListOfBeaches();
        console.log(beaches);
          }, [])
        
  return (
        <div className='parent'>
          <div className='heading'>
            <h1>
              HOME PAGE
            </h1>
          </div>

            <div className="search center" >
            <SearchBar userInput={props.searchBarInput} handleChange={props.handleSearchBarChange} handleSubmit={props.handleSearchBarSubmit}/>
            </div>
              <div className='example-1 center'>
              { beaches[0].beach_name}
              </div>
              <div className='example-2 center'>
              {beaches[1].beach_name}
              </div>
              <div className='example-3 center'>
              {beaches[2].beach_name}
              </div>
              <div className='example-4 center'>
              {beaches[3].beach_name}
              </div>
              <div className='example-5 center'>
              {beaches[4].beach_name}
              </div>
              <div className='example-6 center'>
              {beaches[5].beach_name}
              </div>
              <div className='example-7 center'>
              {beaches[6].beach_name}
              </div>
              <div className='example-8 center'>
              {beaches[7].beach_name}
              </div>
              <div className='example-9 center'>
              {beaches[8].beach_name}
              </div>
        </div>
      );
  };


export default Homepage;