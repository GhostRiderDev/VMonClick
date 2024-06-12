import { useState } from "react";
import MenuPrincipal from "../../Menu/Principal/navBar/MenuPrincipal";
import SidebarButtons from "../../Menu/SidebarButtons";
import Footer from "@/Footer";

function MachineManagement() {
    const [currentPage, setCurrentPage] = useState("Management");

    return (
        <div className="w-full h-screen bg-slate-800">
            <div className="sticky top-0 z-50"><MenuPrincipal /></div>
            <div className="w-full flex flex-row">
                <div className="w-[300px]  sm:block hidden bg-slate-900 lg:h-[683px] md:h-[683px] sm:h-[683px] lg:py-8 pt-2 sticky top-12 md:flex justify-center px-2">
                    <SidebarButtons currentPage={currentPage} setCurrentPage={setCurrentPage} />
                </div>
                <div className="w-full bg-slate-950">

                </div>

            </div>
            <Footer />
        </div>
    )
}
export default MachineManagement;