

import { Routes, Route } from "react-router-dom";
import LoginFrom from "./components/custom/login/LoginFrom";
import Register from "./components/custom/register/Register";
        

  return (
    <>
      <Routes>
        <Route path="/" element={<LoginFrom />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </>

  );
}

export default App;
