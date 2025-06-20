import React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

export default function Estimate() {
  const navigate = useNavigate();
  const location = useLocation();
  // Получаем список выбранных устройств из state
  const { selectedDevices = [] } = location.state || {};

  // Указываем, сколько в среднем экономят одно устройство того или иного типа за месяц
  const savingsRates = {
    Lights: 5,          // $5 в месяц за каждую лампочку
    TV: 7,              // $7 в месяц за каждый телевизор
    Teapot: 2,          // $2 в месяц за каждый чайник
    Clock: 1,           // $1 в месяц за каждый (умный) часы
    Fridge: 10,         // $10 в месяц за каждый холодильник
    'Vacuum cleaner': 4 // $4 в месяц за каждый пылесос
  };
    const monthly = selectedDevices.reduce((sum, item) => {
    const rate = savingsRates[item.name] || 0;
    return sum + rate * item.qty;
  }, 0);

  // Считаем годовую экономию
  const annually = monthly * 12;

  return (
    <div className="table">
      <p>With us you can save up to...</p>
      <p><strong>${monthly}</strong> monthly</p>
      <p>or</p>
      <p><strong>${annually}</strong> annually</p>
      <button onClick={() => navigate('/join')}>
        Book an appointment
      </button>
    </div>
  );
}
