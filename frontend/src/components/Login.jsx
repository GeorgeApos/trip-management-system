import { useState } from "react";
import axios from "axios";
import "./Login.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const authHeader = `Basic ${btoa(`${email}:${password}`)}`;
      const response = await axios.post("http://localhost:8080/login", null, {
        headers: { Authorization: authHeader },
      });

      if (response.status === 200) {
        const role = response.data.role;

        localStorage.setItem("email", email);
        localStorage.setItem("password", password);

        if (role === "citizen") {
          window.location.href = "/citizen"; // Redirect to citizen dashboard
        } else if (role === "travelAgency") {
          window.location.href = "/travelAgency"; // Redirect to agency dashboard
        }
      } else {
        console.error("Login failed");
      }
    } catch (error) {
      console.error("Login error:", error);
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};

export default Login;
