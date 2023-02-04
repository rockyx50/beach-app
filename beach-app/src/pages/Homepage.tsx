import '../App.css';
import { SearchBar } from '../components/SearchBar';
import React, { useState, useEffect } from 'react';
import { Tile } from '../components/Tile';



function Homepage(props:{searchBarInput: any, handleSearchBarChange: any, handleSearchBarSubmit: any, handleTileButtonSubmit: any}) {

  const beachList: {id:0, beach_name:"", state_code:"", county:"", rating:0, beachlength_km:0.0, tier_rank:0}[] = [];
  for (let i = 0; i < 9; i++){
    beachList[i] = {id:0, beach_name:"", state_code:"", county:"", rating:0, beachlength_km:0.0, tier_rank:0};
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
            <div className='grid-container'>
            <div className='example-1 '>
                <Tile beach={beaches[0]} handleSubmit={props.handleTileButtonSubmit}></Tile>
              </div>
              <div className='example-2 '>
                <Tile beach={beaches[1]} handleSubmit={props.handleTileButtonSubmit}></Tile>
              </div>
              <div className='example-3 '>
                <Tile beach={beaches[2]} handleSubmit={props.handleTileButtonSubmit}></Tile>
              </div>
              <div className='example-4 '>
                <Tile beach={beaches[3]} handleSubmit={props.handleTileButtonSubmit}></Tile>
              </div>
              <div className='example-5 '>
                <Tile beach={beaches[4]} handleSubmit={props.handleTileButtonSubmit}></Tile>
              </div>
              <div className='example-6 '>
                <Tile beach={beaches[5]} handleSubmit={props.handleTileButtonSubmit}></Tile>
              </div>
              <div className='example-7 '>
                <Tile beach={beaches[6]} handleSubmit={props.handleTileButtonSubmit}></Tile>
              </div>
              <div className='example-8 '>
                <Tile beach={beaches[7]} handleSubmit={props.handleTileButtonSubmit}></Tile>
              </div>
              <div className='example-9 '>
                <Tile beach={beaches[8]} handleSubmit={props.handleTileButtonSubmit}></Tile>
              </div>
            </div>
              
        </div>
      );
  };


export default Homepage;