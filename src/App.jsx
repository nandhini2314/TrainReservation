import React from 'react';
import { Route, createBrowserRouter, createRoutesFromElements, RouterProvider } from 'react-router-dom';
import UpdateTT from './components/UpdateTT.jsx';
import UpdateForm from './components/UpdateForm.jsx';
import FilterTT from './components/TTSearch.jsx';
import Register from './components/Reserve.jsx';

const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/updateTT" element={<UpdateTT />} />
      <Route path="/updateForm/:trainId" element={<UpdateForm />} />
      <Route path="/displayTT" element={<FilterTT />} />
      <Route path="/reserve" element={<Register />} />
    </>
  )
);

function App() {
  return (
    <RouterProvider router={router} />
  );
}

export default App;
