import MenuPrincipal from "./MenuPrincipal";
import MetricsTesting from "../../metrics/MetricsTesting";
import { RiHomeOfficeFill } from "react-icons/ri";
import { SiBaremetrics } from "react-icons/si";
import { GrVirtualMachine } from "react-icons/gr";
import { FaHandsHelping } from "react-icons/fa";
import { useState } from "react";
import Footer from "@/Footer";


function Menu() {
    const [currentPage, setCurrentPage] = useState("Inicio");

    return (
        <div className=" bg-slate-900  w-full">
            <div className=" flex flex-col top-0 left-0 right-0 z-50 w-full">
                <div className="sticky top-0 z-50"><MenuPrincipal /></div>

                <div className=" w-full flex flex-row">
                    <div className="w-[300px]  sm:block hidden bg-slate-900 lg:h-[683px] lg:py-8 pt-2 sticky top-12 md:flex justify-center px-2">
                        <div className="w-full ">
                            <button
                                onClick={() => setCurrentPage("Inicio")}
                                className={`w-full h-[40px] flex items-center gap-6 text-white pl-2 ${currentPage === "Inicio"
                                    ? "bg-sky-500 lg:text-black  rounded-md"
                                    : "hover:bg-sky-400 hover:text-black hover:rounded-md"
                                    }`}
                            >
                                <RiHomeOfficeFill className="mr-2 " />
                                Inicio
                            </button>
                            <button className="w-full h-[40px] flex items-center gap-6 pl-2 text-white hover:bg-sky-500 hover:text-black hover:rounded-md">
                                <SiBaremetrics className="mr-2" />
                                Metricas
                            </button>
                            <button className="w-full h-[40px] flex items-center gap-6 pl-2 text-white hover:bg-sky-500 hover:text-black hover:rounded-md">
                                <GrVirtualMachine className="mr-2" />
                                Maquinas
                            </button>
                            <button className="w-full h-[40px] flex items-center gap-6 pl-2 text-white hover:bg-sky-500 hover:text-black hover:rounded-md">
                                <FaHandsHelping className="mr-2" />
                                Ayuda
                            </button>
                        </div>
                    </div>
                    <div className="w-full bg-slate-950 px-8 pb-4 space-y-6 lg:py-8 h-max rounded-md ">
                        <div className="w-full text-center space-y-2 lg:text-left lg:space-y-10 lg:pl-[18px]">
                            <h1 className="text-3xl pl-2 pt-4 ">
                                <strong className="text-white">Bienvenido</strong>
                            </h1>
                            <p className="text-2xl text-white">
                                Gestiona tus maquinas virtuales de una manera mas sencilla,comoda
                                y segura.
                            </p>
                        </div>

                        <section>
                            <MetricsTesting />
                        </section>
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
}
export default Menu;
