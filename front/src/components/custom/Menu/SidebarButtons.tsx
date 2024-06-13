import { FaHandsHelping } from "react-icons/fa";
import { GrVirtualMachine } from "react-icons/gr";
import { RiHomeOfficeFill } from "react-icons/ri";
import { SiBaremetrics } from "react-icons/si";
import { Link } from "react-router-dom";
import { IoTerminal } from "react-icons/io5";


function SidebarButtons({ currentPage, setCurrentPage }) {
    return (
        <div className="w-full ">
            <Link
                to='/menu'
                className={`w-full h-[40px] flex items-center gap-6 pl-2 text-white ${currentPage === "Inicio"
                    ? "bg-sky-500 lg:text-black  rounded-md"
                    : "hover:bg-sky-400 hover:text-black hover:rounded-md"
                    }`}>
                <RiHomeOfficeFill className="mr-2 " />
                Inicio
            </Link>
            <Link
                to='/VM'
                onClick={() => setCurrentPage("VM")}
                className={`w-full h-[40px] flex items-center gap-6 text-white pl-2 ${currentPage === "VM"
                    ? "bg-sky-500 lg:text-black  rounded-md"
                    : "hover:bg-sky-400 hover:text-black hover:rounded-md"
                    }`}
            >
                <GrVirtualMachine className="mr-2 " />
                Crear Maquina
            </Link>
            <Link
                to='/machineManagement'
                className={`w-full h-[40px] flex items-center gap-6 pl-2 text-white ${currentPage === "Management"
                    ? "bg-sky-500 lg:text-black  rounded-md"
                    : "hover:bg-sky-400 hover:text-black hover:rounded-md"

                    }`}
            >
                <SiBaremetrics className="mr-2" />
                Maquinas Virtuales
            </Link>
            <Link
                to='/terminal'
                className={`w-full h-[40px] flex items-center gap-6 pl-2 text-white ${currentPage === "Terminal"
                    ? "bg-sky-500 lg:text-black  rounded-md"
                    : "hover:bg-sky-400 hover:text-black hover:rounded-md"

                    }`}
            >
                <IoTerminal className="mr-2" />
                Terminal
            </Link>
            <button className="w-full h-[40px] flex items-center gap-6 pl-2 text-white hover:bg-sky-500 hover:text-black hover:rounded-md">
                <FaHandsHelping className="mr-2" />
                Ayuda
            </button>
        </div >
    );
}
export default SidebarButtons;
