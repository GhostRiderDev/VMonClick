import MenuPrincipal from "../Menu/Principal/navBar/MenuPrincipal";
import { useState } from "react";
import Footer from "@/Footer";
import CreatorVM from "./CreatorVM";
import SidebarButtons from "../Menu/SidebarButtons";

function PrincipalVM() {
    const [currentPage, setCurrentPage] = useState("VM");
    return (
        <div className="w-full h-screen bg-slate-800">
            <div className="sticky top-0 z-50"><MenuPrincipal /></div>
            <div className="w-full flex flex-row ">
                <div className="w-[300px]  sm:block hidden bg-slate-900 lg:h-[683px]  lg:py-8 pt-2 sticky top-12 md:flex justify-center px-2">
                    <SidebarButtons currentPage={currentPage} setCurrentPage={setCurrentPage} />
                </div>
                <div className="w-full bg-slate-950  ">
                    <div className="w-full bg-slate-950 px-8 py-4">
                        <div className="w-full bg-slate-200 rounded-md">
                            <CreatorVM />
                        </div>

                    </div>


                </div>
            </div>
            <Footer />
        </div>
    );
}
export default PrincipalVM;