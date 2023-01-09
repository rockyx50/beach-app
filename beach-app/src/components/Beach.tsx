import { Link } from "react-router-dom";



interface BeachData {
  name: string;
  rating: number;
  description: string;
}

export function Beach(props:{beach:BeachData}){

  return(
  <div>
    <h1>Name: {props.beach.name}</h1>
    <h2>Rating: {props.beach.rating}</h2>
    <h2>{props.beach.description}</h2>
    <Link to={'/Homepage'}>HOME</Link>
  </div>
  );
  }