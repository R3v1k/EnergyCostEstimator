// src/pages/Login.js
import React from 'react';

export default function Login() {
  return (
    <div className="table">
      <h2>Log in through</h2>

      {/* GitHub */}
      <a href="http://localhost:8080/oauth2/authorization/github">
        <button>GitHub</button>
      </a>

      {/* Google */}
      <a href="http://localhost:8080/oauth2/authorization/google">
        <button>Google</button>
      </a>

      {/* Yandex */}
      <a href="http://localhost:8080/oauth2/authorization/yandex">
        <button>Yandex</button>
      </a>
    </div>
  );
}
