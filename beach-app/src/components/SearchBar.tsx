import React, { useState } from 'react';
import './SearchBar.css';
import searchLogo from '../magnifying-glass-solid.svg'





export function SearchBar(props:{userInput: any, handleChange: any, handleSubmit: any}){

  return(
    
  <div >
    {/* <FontAwesomeIcon icon="fa-solid fa-magnifying-glass" /> */}

    <form className="search-box" onSubmit={() => props.handleSubmit()}>
      <button className="btn-search" type="submit" value="Submit"><img src={searchLogo} alt="my image"/></button>
      <input type="text" className="input-search" placeholder="Find a beach" onChange={(event) => props.handleChange(event)}/>
    </form>
    <p>The current state is {props.userInput}</p>
  </div>
  );
  }