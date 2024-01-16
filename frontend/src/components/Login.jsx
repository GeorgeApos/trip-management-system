import { useState } from "react";
import axios from "axios";
import "./Login.css";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();
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

        if (role === "citizen") {
            navigate("/citizen", { state: { authHeader } }); // Redirect to citizen dashboard
        } else if (role === "travelAgency") {
            navigate("/travel-agency", { state: { authHeader } }); // Redirect to travel agency dashboard
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
