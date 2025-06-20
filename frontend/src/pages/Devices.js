import React, { useState } from 'react';
import { Link } from 'react-router-dom';
const availableDevices = [
  'Lights',
  'TV',
  'Teapot',
  'Clock',
  'Fridge',
  'Vacuum cleaner',
];

export default function DeviceSelectorPage() {
  const [showList, setShowList] = useState(false);
  const [selectedDevices, setSelectedDevices] = useState([]);

  const styles = {
    container: {
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      minHeight: '100vh',
      backgroundColor: '#f0f2f5',
      fontFamily: 'Arial, sans-serif',
    },
    cardContainer: {
      position: 'relative',       // важно для абсолютного позиционирования дочерних элементов
      width: '900px',
      padding: '30px',
      paddingBottom: '80px',      // пространство под кнопку
      backgroundColor: '#fff',
      borderRadius: '10px',
      boxShadow: '0 2px 8px rgba(0, 0, 0, 0.1)',
    },
    submitButton: {
      position: 'absolute',
      bottom: '20px',             // отступ от низа контейнера
      left: '50%',                // центрируем по горизонтали
      transform: 'translateX(-50%)',
      padding: '12px 30px',
      backgroundColor: '#28a745',
      color: '#fff',
      border: 'none',
      borderRadius: '4px',
      cursor: 'pointer',
      fontSize: '1rem',
    },
    header: {
      marginBottom: '20px',
      fontSize: '24px',
      color: '#333',
      textAlign: 'center',
    },
    addButton: {
      padding: '10px 20px',
      backgroundColor: '#007bff',
      color: '#fff',
      border: 'none',
      borderRadius: '4px',
      cursor: 'pointer',
      fontSize: '1rem',
      transition: 'background-color 0.2s ease',
      display: 'block',
      margin: '0 auto 10px',
    },
    list: {
      position: 'absolute',
      top: '60px',
      left: 0,
      right: 0,
      backgroundColor: '#fff',
      boxShadow: '0 2px 8px rgba(0, 0, 0, 0.1)',
      borderRadius: '6px',
      zIndex: 1000,
    },
    listItemButton: {
      width: '100%',
      padding: '10px',
      textAlign: 'center',
      background: 'none',
      border: 'none',
      cursor: 'pointer',
      fontSize: '1rem',
    },
    header2: {
      fontSize: '20px',
      fontWeight: '600',
      marginBottom: '10px',
      textAlign: 'center',
      color: '#333',
    },
    devicesContainer: {
      display: 'grid',
      gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))',
      gap: '20px',
      marginTop: '20px',
      justifyItems: 'center',
    },
    card: {
      padding: '15px',
      border: '1px solid #e0e0e0',
      borderRadius: '8px',
      boxShadow: '0 1px 4px rgba(0, 0, 0, 0.1)',
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center',
      width: '100%',
    },
    deviceName: {
      fontSize: '1rem',
      color: '#333',
    },
    quantityContainer: {
      display: 'flex',
      alignItems: 'center',
    },
    qtyButton: {
      width: '30px',
      height: '30px',
      border: 'none',
      borderRadius: '4px',
      backgroundColor: '#e0e0e0',
      cursor: 'pointer',
      fontSize: '1rem',
    },
    qtyText: {
      margin: '0 10px',
    },
    deleteButton: {
      marginLeft: '10px',
      padding: '5px 10px',
      border: 'none',
      borderRadius: '4px',
      backgroundColor: '#dc3545',
      color: '#fff',
      cursor: 'pointer',
      fontSize: '0.9rem',
    },
    controlsContainer: {
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'flex-end',
    },
  };

  const handleAddClick = () => {
    setShowList(prev => !prev);
  };

  const handleSelectDevice = (device) => {
    setSelectedDevices(prev => {
      // If already selected, ignore
      if (prev.some(item => item.name === device)) return prev;
      // Add with initial quantity 1
      return [...prev, { name: device, qty: 1 }];
    });
    setShowList(false);
  };

  const updateQty = (deviceName, delta) => {
    setSelectedDevices(prev => 
      prev.map(item => {
        if (item.name !== deviceName) return item;
        const newQty = item.qty + delta;
        return { ...item, qty: newQty > 1 ? newQty : 1 };
      })
    );
  };

  const handleRemoveDevice = (deviceName) => {
    setSelectedDevices(prev => prev.filter(item => item.name !== deviceName));
  };

  return (
    <div style={styles.container}>
      <div style={styles.cardContainer}>
      <h1 style={styles.header}>Add device</h1>
      <button style={styles.addButton} onClick={handleAddClick}>
        Add
      </button>

      {showList && (
        <ul style={styles.list}>
          {availableDevices.map(device => (
            <li key={device}>
              <button
                style={styles.listItemButton}
                onClick={() => handleSelectDevice(device)}
              >
                {device}
              </button>
            </li>
          ))}
        </ul>
      )}

      {selectedDevices.length > 0 && (
        <div className="mt-6">
          <h2 style={styles.header2}>Your devices:</h2>
          <div style={styles.devicesContainer}>
            {selectedDevices.map(item => (
              <div key={item.name} style={styles.card}>
                <span style={styles.deviceName}>{item.name}</span>
                <div style={styles.controlsContainer}>
                  <button style={styles.qtyButton} onClick={() => updateQty(item.name, -1)}>
                    -
                  </button>
                  <span style={styles.qtyText}>{item.qty}</span>
                  <button style={styles.qtyButton} onClick={() => updateQty(item.name, 1)}>
                    +
                  </button>
                  <button style={styles.deleteButton} onClick={() => handleRemoveDevice(item.name)}>
                    Delete
                  </button>
                </div>
              </div>
            ))}
          </div>
        </div>
      )}
      </div>
      <Link to="/estimate" state={{ selectedDevices }}>
        <button style={styles.submitButton}>
          Estimate
        </button>
      </Link>
    </div>
  );
}