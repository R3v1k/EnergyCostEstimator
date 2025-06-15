import React from 'react';
import { useNavigate } from 'react-router-dom';

export default function Estimate() {
  const navigate = useNavigate();
  // здесь вы можете вставить логику вычисления
  const monthly = '$120'; const annually = '$1440';

  return (
    <div className="table">
      <p>With us you can save up to...</p>
      <p><strong>{monthly}</strong> monthly</p>
      <p>or</p>
      <p><strong>{annually}</strong> annually</p>
      <button onClick={() => navigate('/appointment')}>
        Book an appointment
      </button>
    </div>
  );
}
