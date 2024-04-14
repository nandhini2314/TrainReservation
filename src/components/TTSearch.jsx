import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Typography, FormControl, InputLabel, Select, MenuItem, TextField, Button, Container, Grid, Paper, Card, CardContent, CardActions, styled } from '@mui/material';
import { Link } from 'react-router-dom';

function TTSearch() {
  const [pathNames, setPathNames] = useState([]);
  const [selectedPathName, setSelectedPathName] = useState('');
  const [selectedDate, setSelectedDate] = useState('');
  const [filteredTT, setFilteredTT] = useState([]);
  const StyledCard = styled(Card)({
    backgroundColor: '#f9a1a1', // Lighter tone of red
    transition: 'transform 0.3s',
    '&:hover': {
      transform: 'scale(1.05)', // Enlarge on hover
      boxShadow: '0px 3px 6px rgba(0, 0, 0, 0.1)' // Add shadow on hover
    }
  });

  useEffect(() => {
    // Fetch dropdown options dynamically from another microservice
    axios.get('http://localhost:8084/api/location/allPathNames')
      .then(response => {
        setPathNames(response.data);
      })
      .catch(error => {
        console.error('Error fetching path names:', error);
      });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post('http://localhost:8083/api/timetable/filterTT', {
      filterDate: selectedDate,
      filterPathName: selectedPathName
    })
    .then(response => {
      // Update the filtered TT models
      setFilteredTT(response.data);
    })
    .catch(error => {
      console.error('Error filtering TT:', error);
    });
  };

  return (
    <Container maxWidth="md">
      <Paper elevation={3} sx={{ padding: 2, marginBottom: 4 }}>
        <Typography variant="h4" gutterBottom>TT Search</Typography>
        <form onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item xs={12} md={6}>
              <FormControl fullWidth>
                <InputLabel id="pathName-label">Select Path Name</InputLabel>
                <Select
                  labelId="pathName-label"
                  id="pathName"
                  value={selectedPathName}
                  onChange={(e) => setSelectedPathName(e.target.value)}
                >
                  <MenuItem value="">
                    <em>Select</em>
                  </MenuItem>
                  {pathNames.map((pathName, index) => (
                    <MenuItem key={index} value={pathName}>{pathName}</MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} md={6}>
              <TextField
                id="date"
                label="Select Date"
                type="date"
                fullWidth
                value={selectedDate}
                onChange={(e) => setSelectedDate(e.target.value)}
                InputLabelProps={{ shrink: true }}
              />
            </Grid>
          </Grid>
          <Button type="submit" variant="contained" color="primary" sx={{ marginTop: 2 }}>Submit</Button>
        </form>
      </Paper>

      {/* Display filtered TT models as cards */}
 <Grid container spacing={2}>
        {filteredTT.map((tt, index) => (
          <Grid item xs={12} key={index}>
            {/* Custom styled Card component */}
            <StyledCard variant="outlined">
              <CardContent>
                <Typography variant="h5" component="div">Train Path Name: {tt.trainPathName}</Typography>
                <Typography variant="body1">Date: {new Date(tt.date).toLocaleDateString()}</Typography>
                <Typography variant="body1">Capacity: {tt.capacity}</Typography>
              </CardContent>
              <CardActions>
                <Link to={`/reserve?id=${tt.id}&pathName=${tt.trainPathName}`} underline="none">
                  <Button variant="contained" color="secondary">Register</Button>
                </Link>
              </CardActions>
            </StyledCard>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
}

export default TTSearch;
