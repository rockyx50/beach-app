import React, { useState } from 'react';
import { Link } from "react-router-dom";



interface BeachData {
  name: string;
  rating: number;
  description: string;
}

export function Beach(props:{beach:BeachData}){

  const [beachData, setBeachData] = useState(props.beach);
  console.log(props.beach)


  return(
  <div>
    <h1>Name: {beachData.name}</h1>
    <h2>Rating: {beachData.rating}</h2>
    <h2>{beachData.description}</h2>
    <Link to={'/Homepage'}>HOME</Link>
  </div>
  );
  }