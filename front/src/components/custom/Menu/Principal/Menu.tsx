import MenuPrincipal from "./MenuPrincipal";
import MetricsTesting from "../../metrics/MetricsTesting";
import { RiHomeOfficeFill } from "react-icons/ri";
import { SiBaremetrics } from "react-icons/si";
import { GrVirtualMachine } from "react-icons/gr";
import { FaHandsHelping } from "react-icons/fa";
import { useState } from "react";




function Menu() {
    const [currentPage, setCurrentPage] = useState('Inicio');
    return (
        <div className="w-full bg-slate-100">
            <div className="w-full flex flex-col top-0 left-0 right-0 z-50">
                <MenuPrincipal />
                <div className=" w-full flex flex-row ">
                    <div className="w-[300px] lg:block md:block sm:block hidden bg-[#0a0a0ae5] lg:h-[220px] lg:py-8">
                        <div className="w-full ">
                            <button onClick={() => setCurrentPage('Inicio')} className={`w-full h-[40px] flex items-center gap-6 text-white pl-2 ${currentPage === 'Inicio' ? 'bg-sky-400 text-black rounded-md' : 'hover:bg-sky-400 hover:text-black hover:rounded-md'}`}>
                                <RiHomeOfficeFill className="mr-2 " />
                                Inicio
                            </button>
                            <button className="w-full h-[40px] flex items-center gap-6 pl-2 text-white hover:bg-sky-400 hover:text-black hover:rounded-md">
                                <SiBaremetrics className="mr-2" />
                                Metricas</button>
                            <button className="w-full h-[40px] flex items-center gap-6 pl-2 text-white hover:bg-sky-400 hover:text-black hover:rounded-md">
                                <GrVirtualMachine className="mr-2" />
                                Maquinas</button>
                            <button className="w-full h-[40px] flex items-center gap-6 pl-2 text-white hover:bg-sky-400 hover:text-black hover:rounded-md">
                                <FaHandsHelping className="mr-2" />
                                Ayuda</button>
                        </div>
                    </div>
                    <div className="w-full bg-[#0a0a0ae5] px-8 pb-4 space-y-6 lg:py-8">
                        <h1 className="text-3xl pl-2 pt-4 "><strong className="text-white">Bienvenido</strong></h1>
                        <p className="text-2xl text-white">Gestiona tus maquinas virtuales de una manera mas sencilla,comoda y segura.</p>
                    </div>
                </div>
            </div>
            <section>
                <MetricsTesting />
            </section>

        </div>
    );
}
export default Menu;