import React, { useState } from 'react';


export function SearchBar(){

  // handleSubmit(i){
  // alert('A beach was searched': props.value)
  // }

  const [userInput, setUserInput] = useState('a');
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUserInput(event.target.value)
    console.log(userInput)
  }
  
  const handleSubmit = () => {
    console.log(userInput)
  }


  return(
  <div>
    <form >
          <label>
            <input type="text" placeholder='Find a beach' onChange={(event) => handleChange(event)}/>
          </label>
          <input type="submit" value="Submit" onSubmit={() => handleSubmit()} />
    </form>
    <p>The current state is {userInput}</p>
  </div>
  );
  }