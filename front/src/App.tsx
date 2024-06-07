

import { Routes, Route } from "react-router-dom";
import LoginFrom from "./components/custom/login/LoginFrom";
import Register from "./components/custom/register/Register";
import Menu from "./components/custom/Menu/Principal/Menu";
function App() {


  return (
    <>
      <Routes>
        <Route path="/" element={<LoginFrom />} />
        <Route path="/register" element={<Register />} />
        <Route path="/menu" element={<Menu />} />
      </Routes>
    </>

  );
}

export default App;
