import React, { useState } from 'react';
import '../App.css';
import { BeachData } from './Beach';
import ReactStars from 'react-stars';



export function Tile(props:{beach:BeachData, handleSubmit: any}){
// console.log(props.beach.beachlength_km)
  return(
  <div className='button'>
    <button className='button' type='button' onClick={() => props.handleSubmit(props.beach)}>
      <div className='button-rating'>
      { props.beach.rating.toFixed(1)}
      <ReactStars count={1} edit={false} size={24} color1={'#ffd700'} />
      </div>
      <div className='button-contnt'>
        { props.beach.beach_name}
      </div>
      {/* Beach Length: { props.beach.beachlength_km} */}
    </button>
  </div>
  );
  }