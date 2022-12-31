import logo from '../logo.svg';
import '../App.css';
import { SearchBar } from '../components/SearchBar';
import React from 'react';

function Homepage(props:{searchBarInput: any, handleSearchBarChange: any, handleSearchBarSubmit: any}) {
    return (
        <div >
            
            <h1>
              HOME PAGE
            </h1>
            <SearchBar userInput={props.searchBarInput} handleChange={props.handleSearchBarChange} handleSubmit={props.handleSearchBarSubmit}/>
        </div>
      );
  };


export default Homepage;