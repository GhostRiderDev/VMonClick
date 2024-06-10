import { Routes, Route } from "react-router-dom";
import LoginFrom from "./components/custom/login/LoginFrom";
import Register from "./components/custom/register/Register";
import Menu from "./components/custom/Menu/Principal/Menu";
import ProtectedRoute from "./middleware/ProtectedRoute";
import TerminalConn from "./components/custom/terminal/TerminalConn";
import PrincipalVM from "./components/custom/VM/PrincipalVM";
import MachineManagement from "./components/custom/VM/MachineManagement/MachineManagement";



function App() {
  const token = localStorage.getItem('token');


  return (
    <>
      <Routes>
        <Route path="/" element={<LoginFrom />} />
        <Route path="/" element={<LoginFrom />} />
        <Route path="/register" element={<Register />} />
        <Route element={<ProtectedRoute canActivate={token} />}>
          <Route path="/menu" element={<Menu />} />
          <Route path="/VM" element={<PrincipalVM />} />
          <Route path="/machineManagement" element={<MachineManagement />} />
        </Route>
        <Route path="/terminal" element={<TerminalConn />} />
      </Routes>
    </>
  );
}

export default App;
