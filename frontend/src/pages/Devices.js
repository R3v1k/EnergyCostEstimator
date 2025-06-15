import React from 'react';
import { useNavigate } from 'react-router-dom';

export default function Devices() {
  const navigate = useNavigate();
  const list = ['Fridge','TV','Washer'];

  return (
    <div className="table">
      <h3>Which devices do you have at home?</h3>
      <div className="devices-list">
        {list.map(name => (
          <div className="device-card" key={name}>
            <p>{name}</p>
            <button>Select</button>
          </div>
        ))}
      </div>
      <button onClick={() => navigate('/start')}>Estimate</button>
    </div>
  );
}
