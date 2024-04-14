import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Typography, FormControl, InputLabel, Select, MenuItem, TextField, Button, Container, Grid, Paper, IconButton } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import { useLocation } from 'react-router-dom';

function Reserve() {
  const [stations, setStations] = useState([]);
  const [selectedPathName, setSelectedPathName] = useState('');
  const [origin, setOrigin] = useState('');
  const [destination, setDestination] = useState('');
  const [passengerNames, setPassengerNames] = useState(['']);

  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const idFromQuery = searchParams.get('id');
  const pathNameFromQuery = searchParams.get('pathName');

  useEffect(() => {
    if (pathNameFromQuery) {
      setSelectedPathName(pathNameFromQuery);
    }
  }, [pathNameFromQuery]);

  useEffect(() => {
    if (selectedPathName) {
      axios.get(`http://localhost:8084/api/location/getStations/${selectedPathName}`)
        .then(response => {
          setStations(response.data);
        })
        .catch(error => {
          console.error('Error fetching stations:', error);
        });
    }
  }, [selectedPathName]);

  const handleAddPassenger = () => {
    setPassengerNames([...passengerNames, '']);
  };

  const handleChangePassenger = (index, value) => {
    const updatedPassengerNames = [...passengerNames];
    updatedPassengerNames[index] = value;
    setPassengerNames(updatedPassengerNames);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Handle form submission
  };

  return (
    <Container maxWidth="md">
      <Paper elevation={3} sx={{ padding: 2, marginBottom: 4 }}>
        <Typography variant="h4" gutterBottom>Reservation</Typography>
        <form onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item xs={12} md={6}>
              <FormControl fullWidth>
                <InputLabel id="origin-label">Origin</InputLabel>
                <Select
                  labelId="origin-label"
                  id="origin"
                  value={origin}
                  onChange={(e) => {
                    setOrigin(e.target.value);
                    setSelectedPathName(e.target.value);
                  }}
                >
                  <MenuItem value="">
                    <em>Select</em>
                  </MenuItem>
                  {/* Map through stations for origin dropdown */}
                  {stations.map((station, index) => (
                    <MenuItem key={index} value={station}>{station}</MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} md={6}>
              <FormControl fullWidth>
                <InputLabel id="destination-label">Destination</InputLabel>
                <Select
                  labelId="destination-label"
                  id="destination"
                  value={destination}
                  onChange={(e) => setDestination(e.target.value)}
                >
                  <MenuItem value="">
                    <em>Select</em>
                  </MenuItem>
                  {/* Map through stations for destination dropdown */}
                  {stations.map((station, index) => (
                    <MenuItem key={index} value={station}>{station}</MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
          </Grid>
          {/* Passenger names section */}
          {passengerNames.map((passenger, index) => (
            <TextField
              key={index}
              fullWidth
              label={`Passenger ${index + 1}`}
              value={passenger}
              onChange={(e) => handleChangePassenger(index, e.target.value)}
              sx={{ marginTop: 2 }}
            />
          ))}
          {/* Add passenger button */}
          <IconButton onClick={handleAddPassenger} sx={{ marginTop: 2 }}>
            <AddIcon />
          </IconButton>
          {/* Submit button */}
          <Button type="submit" variant="contained" color="primary" sx={{ marginTop: 2 }}>Submit</Button>
        </form>
      </Paper>
    </Container>
  );
}

export default Reserve;
