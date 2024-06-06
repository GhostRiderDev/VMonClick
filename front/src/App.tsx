import { Routes, Route } from "react-router-dom";
import LoginFrom from "./components/custom/login/LoginFrom";
import Register from "./components/custom/register/Register";
function App() {

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
// <div className="w-full h-screen  flex justify-center items-center bg-[url(/datacenter.jpg)] bg-cover bg-center" >
//   <div className=" pt-6"><LoginFrom /></div>

// </div>
