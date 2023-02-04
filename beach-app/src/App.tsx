import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { SearchBar } from './components/SearchBar';
import { BrowserRouter, Route, Routes, useNavigate  } from "react-router-dom";
import Homepage from './pages/Homepage';
import Notfound from './pages/Notfound';
import { Beach, BeachData } from './components/Beach';
import { Tile } from './components/Tile';



function App() {
  const [userInput, setUserInput] = useState('');
  const [beach, setBeach] = useState({id:0, beach_name:"", state_code:"", county:"", rating:0, beachlength_km:0, tier_rank:0});
  const navigate = useNavigate();

  const handleSearchBarChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUserInput(event.target.value)
    console.log(userInput)
  }
  
  const handleSearchBarSubmit = () => {
    // setUserInput(userInput.toLowerCase())
    fetch(`http://localhost:8080/beachappbackend/getBeachInfo?beachName=${userInput}`,{method:'GET'})
         .then((response) => {
          if (!response.ok) {
            throw new Error(response.statusText)
          }
          return response.json() as Promise<Array<typeof beach> >
        })
         .then((data) => {
          setUserInput("");
          if (data.length > 0){
            setBeach(data[0]);
            navigate(`/${userInput}`);
          } 
         })
         .catch((err) => {
            console.log(err.message);
         });
         navigate("notfound");
  }
  const handleTileButtonSubmit = (beachInput:BeachData) => {
    setBeach(beachInput);
    navigate(`/${beachInput.beach_name}`);
  }

  return (
        <Routes>
          <Route path="/" element={<Homepage searchBarInput={userInput} handleSearchBarChange ={handleSearchBarChange} handleSearchBarSubmit={handleSearchBarSubmit} handleTileButtonSubmit={handleTileButtonSubmit}/>} />
          <Route path="/Homepage" element={<Homepage searchBarInput={userInput} handleSearchBarChange ={handleSearchBarChange} handleSearchBarSubmit={handleSearchBarSubmit} handleTileButtonSubmit={handleTileButtonSubmit}/>} />
          <Route path="notfound" element={<Notfound />} />
          <Route path=':userInput' element={<Beach beach={beach}/>} />
        </Routes>
  );
}

export default App;
