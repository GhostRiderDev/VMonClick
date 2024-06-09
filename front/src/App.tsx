

import { Routes, Route } from "react-router-dom";
import LoginFrom from "./components/custom/login/LoginFrom";
import Register from "./components/custom/register/Register";
import Menu from "./components/custom/Menu/Principal/Menu";
import ProtectedRoute from "./middleware/ProtectedRoute";



function App() {
  const token = sessionStorage.getItem('token');



  return (
    <>
      <Routes>
        <Route path="/" element={<LoginFrom />} />
        <Route path="/" element={<LoginFrom />} />
        <Route path="/register" element={<Register />} />
        <Route element={<ProtectedRoute canActivate={token} />}>
          <Route path="/menu" element={<Menu />} />
        </Route>
      </Routes >
    </>

  );
}

export default App;
