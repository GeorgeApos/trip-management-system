import { useState } from "react";
import axios from "axios";
import "./Register.css";

const Register = () => {
  const [vat, setVat] = useState("");
  const [name1, setName1] = useState("");
  const [name2, setName2] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("citizen"); // Default to citizen

  const handleRegister = async () => {
    try {
      const authHeader = `Basic ${btoa(`${email}:${password}`)}`;
      const url = `http://localhost:8080/register?vat=${vat}&name1=${name1}&name2=${userType === "citizen" ? name2 : ""}`;

      const response = await axios({
        method: 'post',
        url,
        headers: {
          'Authorization': authHeader,
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Access-Control-Allow-Origin': '*',
        },
      });

      if (response.status === 200) {
        localStorage.setItem("email", email);
        localStorage.setItem("password", password);
        if (userType === "citizen") {
          window.location.href = "/citizen";
        } else if (userType === "travelAgency") {
          window.location.href = "/travelAgency";
        }
      } else {
        console.error("Registration failed");
      }
    } catch (error) {
      console.error("Register error:", error);
    }
  };

  const handleUserTypeChange = (e) => {
    setUserType(e.target.value);
    if (e.target.value === "citizen") {
      setName2("");
    }
  };

  return (
    <div>
      <h2>Register</h2>
      <select value={userType} onChange={handleUserTypeChange}>
        <option value="citizen">Citizen</option>
        <option value="travelAgency">Travel Agency</option>
      </select>
      <input type="text" placeholder="VAT" value={vat} onChange={(e) => setVat(e.target.value)} />
      <input type="text" placeholder="First Name / Company Name" value={name1} onChange={(e) => setName1(e.target.value)} />
      {userType === "citizen" && ( // Show name2 input only for citizens
        <input type="text" placeholder="Last Name" value={name2} onChange={(e) => setName2(e.target.value)} />
      )}
      <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button onClick={handleRegister}>Register</button>
    </div>
  );
};

export default Register;
