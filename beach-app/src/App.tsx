import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { SearchBar } from './components/SearchBar';
import { BrowserRouter, Route, Routes, useNavigate  } from "react-router-dom";
import Homepage from './pages/Homepage';
import { Beach } from './components/Beach';


function App() {
  const [userInput, setUserInput] = useState('');
  const [beach, setBeach] = useState({name:"", rating:0, description:""});
  const navigate = useNavigate();

  const handleSearchBarChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUserInput(event.target.value)
    console.log(userInput)
  }
  
  const handleSearchBarSubmit = () => {
    setBeach({name:userInput, rating:3, description:"This is a test."}); //Need to get beach data from api call
    console.log(beach)
    navigate(`/${userInput}`);
  }

  console.log(beach)

  return (
        <Routes>
          <Route path="/" element={<Homepage searchBarInput={userInput} handleSearchBarChange ={handleSearchBarChange} handleSearchBarSubmit={handleSearchBarSubmit}/>} />
          <Route path="/Homepage" element={<Homepage searchBarInput={userInput} handleSearchBarChange ={handleSearchBarChange} handleSearchBarSubmit={handleSearchBarSubmit}/>} />

          <Route path=':userInput' element={<Beach beach={beach}/>} />

        </Routes>
  );
}

export default App;
