import React from 'react';
import { Link } from 'react-router-dom';
export default function Join() {
  return (
    <div className="table">
      <h2>Join us!</h2>
      <input placeholder="E-mail" type="email" />
      <input placeholder="Login" />
      <input placeholder="Password" type="password" />
      <Link to="/devices"><button>Proceed</button></Link>
    </div>
  );
}
