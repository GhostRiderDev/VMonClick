import { useState } from "react";
import MenuPrincipal from "../Menu/Principal/navBar/MenuPrincipal";
import SidebarButtons from "../Menu/SidebarButtons";
import Footer from "@/Footer";
import { IoTerminal } from "react-icons/io5";
import TerminalConn from "../VM/Terminal/TerminalConn";
import MetricsTesting from "../metrics/MetricsTesting";

function TerminalGestion() {
    const [currentPage, setCurrentPage] = useState("Terminal");
    return (<>
        <div className="sticky top-0 z-50"><MenuPrincipal /></div>
        <div className="w-full h-screen bg-slate-800">
            <div className="w-full flex flex-row">
                <div className="w-[300px]  sm:block hidden bg-slate-900  lg:py-8 pt-2 sticky top-12 md:flex justify-center px-2">
                    <SidebarButtons currentPage={currentPage} setCurrentPage={setCurrentPage} />
                </div>
                <div className="w-full bg-[url(https://wallpaper.forfun.com/fetch/0e/0e912bbd7897b97af764931d92aedbe3.jpeg)] bg-cover px-8 pb-2 pt-2">
                    <div className="w-full">
                        <div className="bg-opacity-80 bg-slate-200 rounded-md  ">
                            <div className="flex items-center">
                                <h1 className="text-2xl pt-2 pl-6"><strong>Terminal</strong></h1>
                                <IoTerminal className="text-2xl pl-6 text-center pt-2" size="2em" />
                            </div>
                            <p className="pl-6">Terminal de maquina inicializada</p>
                            <div className="pt-2 px-6 pb-2">
                                <TerminalConn />
                            </div>
                        </div>
                        <div className="bg-opacity-80 bg-slate-200  rounded-b-md">
                            <div className="flex items-center">
                                <h1 className="text-2xl pt-2 pl-6"><strong>Metricas de maquina</strong></h1>
                            </div>
                            <div>
                                <MetricsTesting cpuData={null} ramData={null} diskData={null} />
                            </div>
                        </div>


                    </div>
                </div>


            </div>
            <Footer />
        </div>
    </>
    );
}
export default TerminalGestion;