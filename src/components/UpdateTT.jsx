// UpdateTT.jsx
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import './UpdateTT.css'; 

function UpdateTT() {
  const [trains, setTrains] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8081/api/train/getAll')
      .then(response => {
        setTrains(response.data);
      })
      .catch(error => {
        console.error('Error fetching trains data:', error);
      });
  }, []);

  return (
    <div className="container">
      <div className="train-cards">
        {trains.map(train => (
          <div key={train.trainId} className="train-card">
            <h3>{train.trainName}</h3>
            <p>ID: {train.trainId}</p>
            <p>Type: {train.trainType}</p>
            <p>Route: {train.trainRoute}</p>
            <p>Fare: {train.trainFare}</p>
            <p>Capacity: {train.trainCapacity}</p>
            <img src={train.trainImage} alt={train.trainName} />
            <Link to={`/updateForm/${train.trainId}`}>
              <button className="button">Update TimeTable</button>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
}

export default UpdateTT;
