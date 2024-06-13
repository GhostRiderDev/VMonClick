import { useState } from "react";
import MenuPrincipal from "../../Menu/Principal/navBar/MenuPrincipal";
import SidebarButtons from "../../Menu/SidebarButtons";
import Footer from "@/Footer";
import InitialVM from "../initial/InitialVM";

import MetricsTesting from "../../metrics/MetricsTesting";

function MachineManagement() {
    const [currentPage, setCurrentPage] = useState("Management");


    return (
        <>
            <div className="sticky top-0 z-50"><MenuPrincipal /></div>
            <div className="w-full h-screen bg-slate-800">

                <div className="w-full flex flex-row">
                    <div className="w-[300px]  sm:block hidden bg-slate-900  lg:py-8 pt-2 sticky top-12 md:flex justify-center px-2">
                        <SidebarButtons currentPage={currentPage} setCurrentPage={setCurrentPage} />
                    </div>
                    <div className="w-full bg-[url(https://www.muycomputer.com/wp-content/uploads/2018/08/Maquinas_virtuales.jpg)] px-8 pb-2 pt-2">
                        <div className="w-full">
                            <div className="bg-opacity-80 bg-slate-200 rounded-t-md ">
                                <div className="rounded-md  ">
                                    <h1 className="flex justify-items-start text-2xl pt-2 pl-6"><strong>Maquinas Virtuales Instaladas</strong></h1>
                                    <p className="pl-6">recuadro de maquinas virtuales instalas</p>
                                    <InitialVM />
                                </div>
                            </div>

                            <div className="bg-opacity-80 bg-slate-200  rounded-b-md">
                                <div className="flex items-center">
                                    <h1 className="text-2xl pt-2 pl-6"><strong>Metricas de maquinas </strong></h1>
                                </div>
                                <div>
                                    <MetricsTesting cpuData={null} ramData={null} diskData={null} />
                                </div>
                            </div>
                        </div>
                    </div>
                </div >
                <Footer />
            </div >
        </>
    )
}
export default MachineManagement;