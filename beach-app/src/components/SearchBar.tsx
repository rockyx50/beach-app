import React, { useState } from 'react';
import '../App.css';




export function SearchBar(props:{userInput: any, handleChange: any, handleSubmit: any}){

  return(
  <div>
    <form onSubmit={() => props.handleSubmit()}>
          <label>
            <input type="text" placeholder='Find a beach' onChange={(event) => props.handleChange(event)}/>
          </label>
          <input type="submit" value="Submit" />
    </form>
    <p>The current state is {props.userInput}</p>
  </div>
  );
  }