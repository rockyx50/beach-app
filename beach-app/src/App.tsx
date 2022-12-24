import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';

function SearchBar(){

  // handleSubmit(i){
  // alert('A beach was searched': props.value)
  // }

  const [userInput, setUserInput] = useState('');
  const handleChange = (event.target.value: string) => {
    setUserInput(event);
  }
  
  return(<form >
          <label>
            <input type="text" placeholder='Find a beach' onChange={() => setUserInput}/>
          </label>
          <input type="submit" value="Submit" />
        </form>);
  }


function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <SearchBar />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
