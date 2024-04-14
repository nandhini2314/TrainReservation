import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom'; // 
import axios from 'axios';
import 'tailwindcss/tailwind.css';
import Button from '@mui/material/Button';


function UpdateForm() {
  const { trainId } = useParams();
  const [trainCapacity,setTrainCapacity] = useState(0);
  const navigate = useNavigate();
  

  useEffect(() => {
    axios.get(`http://localhost:8081/api/train/getCapacity/${trainId}`)
      .then(response => {
        // Handle the response data here
        console.log(response.data);
        setTrainCapacity(response.data); // Assuming the response data is the train capacity
      })
      .catch(error => {
        // Handle errors
        console.error('Error fetching train capacity:', error);
      });
  }, [trainId]); // Make sure to include trainId in the dependency array to re-run the effect when it changes

  const [trainPathNames, setTrainPathNames] = useState([]);
  const [formData, setFormData] = useState({
    trainId: trainId,
    trainPathName: '',
    date: '',
    capacity: trainCapacity
  });

  useEffect(() => {
    setFormData(prevState => ({
      ...prevState,
      capacity: trainCapacity
    }));
  }, [trainCapacity]);

  useEffect(() => {
    axios.get('http://localhost:8084/api/location/allPathNames')
      .then(response => {
        setTrainPathNames(response.data);
      })
      .catch(error => {
        console.error('Error fetching train path names:', error);
      });
  }, []);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Make a POST request to another microservice with form data
    axios.post('http://localhost:8083/api/timetable', formData)
      .then(response => {
        console.log('Train added successfully:', response.data);
        navigate('/updateTT');        
        // Reset the form data after successful submission

      })
      .catch(error => {
        console.error('Error adding train:', error);
      });
  };


  return (
    <div>

      <h2 className="text-center">ID: {trainId}</h2>
      <form onSubmit={handleSubmit} className="max-w-md mx-auto mt-8 p-6 bg-white rounded-lg shadow-lg">
  <div className="mb-4">
    <label htmlFor="trainPathName" className="block text-gray-700 text-sm font-bold mb-2">Train Path Name:</label>
    <select id="trainPathName" name="trainPathName" value={formData.trainPathName} onChange={handleChange} className="w-full px-3 py-2 border rounded-lg shadow-sm focus:outline-none focus:border-indigo-500">
      <option value="">Select</option>
      {trainPathNames.map((pathName, index) => (
        <option key={index} value={pathName}>{pathName}</option>
      ))}
    </select>
  </div>
  <div className="mb-4">
    <label htmlFor="date" className="block text-gray-700 text-sm font-bold mb-2">Date:</label>
    <input type="date" id="date" name="date" value={formData.date} onChange={handleChange} className="w-full px-3 py-2 border rounded-lg shadow-sm focus:outline-none focus:border-indigo-500" />
  </div>
  <div className="mb-4">
    <label htmlFor="capacity" className="block text-gray-700 text-sm font-bold mb-2">Capacity:</label>
    <input disabled type="text" id="capacity" name="capacity" value={formData.capacity} onChange={handleChange} className="w-full px-3 py-2 border rounded-lg shadow-sm bg-gray-200" />
  </div>
  <Button type="submit" className="w-full bg-indigo-500 text-white font-bold py-2 px-4 rounded-lg hover:bg-indigo-700 focus:outline-none focus:bg-indigo-700">Submit</Button>
</form>
    </div>
  );
}

export default UpdateForm;
