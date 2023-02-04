import { Link } from "react-router-dom";
import './BeachPage.css';

export interface BeachData {
  id: number;
  beach_name: string;
  state_code: string;
  county: string;
  rating: number;
  beachlength_km: number;
  tier_rank: number;
}

export function Beach(props:{beach:BeachData}){
  return(
  <div className="parent">
    <div className="heading">
      <Link to={'/Homepage'}>HOME</Link>
    </div>
    <div className="name center column-aligned">
      <h1>Name: {props.beach.beach_name}</h1>
      <h2>Rating: {props.beach.rating}</h2>
    </div>
    <div className="info center">
      <div className="beach-info center">
        <h2>Last Cleaned on: </h2>
        <h2>{props.beach.county}, {props.beach.state_code}</h2>
        <h2>Beach Length: {props.beach.beachlength_km * 1000}m</h2>
      </div>
    </div>
    <div className="history center">
      HISTORY
    </div>
  </div>
  );
  }