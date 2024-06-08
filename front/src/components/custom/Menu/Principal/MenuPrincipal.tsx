import { Link } from "react-router-dom";
import { Button } from "@/components/ui/button"
import { Sheet, SheetTrigger, SheetContent } from "@/components/ui/sheet"
import { MountainIcon } from "lucide-react";
import { MenuIcon } from "lucide-react";
function MenuPrincipal() {
    return (
        <>
            <header className="flex h-[70px] w-full shrink-0 items-center px-4 md:px-6 bg-slate-900 border-b border-slate-800 rounded-sm ">
                <Link to="#" className="flex items-center justify-center text-sky-500" >
                    <MountainIcon className="h-6 w-6 " /> <strong className="text-sky-500 text-xl">VMonClick</strong>
                    <span className="sr-only">VMonClick</span>
                </Link>
                <nav className="ml-auto flex justify-center items-center gap-4 sm:gap-4">
                    <span className="text-white text-lg hidden sm:block">Keni Beck</span>
                    <img className="w-10 h-10 rounded-full border border-sky-400" src="https://gravatar.com/avatar/ef938b7fcb3aeaf9e148226bb76bcfcf?s=400&d=robohash&r=x" />

                    {/* <Link
                        to="#"
                        className="text-sm font-medium hover:underline underline-offset-4 hidden sm:block"

                    >
                        Gestionar Maquinas
                    </Link>
                    <Link
                        to="#"
                        className="text-sm font-medium hover:underline underline-offset-4 hidden sm:block"

                    >
                        Análisis
                    </Link> */}
                    <Link
                        to="#"
                        className="text-sm font-medium hover:underline underline-offset-4 hidden sm:block"

                    />
                    <Button variant="outline" size="sm" className="hidden sm:block hover:bg-sky-500 hover:border-none hover:w-[60px]">
                        Salir
                    </Button>
                    <Sheet>
                        <SheetTrigger asChild>
                            <Button variant="outline" size="icon" className="sm:hidden">
                                <MenuIcon className="h-6 w-6" />
                                <span className="sr-only">Menu</span>
                            </Button>
                        </SheetTrigger>
                        <SheetContent side="right">
                            <div className="grid gap-2 py-6 ">
                                <Link to="#" className="flex w-full items-center py-2 text-lg font-semibold" >
                                    Virtual Machines
                                </Link>
                                <Link to="#" className="flex w-full items-center py-2 text-lg font-semibold" >
                                    Users
                                </Link>
                                <Link to="#" className="flex w-full items-center py-2 text-lg font-semibold">
                                    Settings
                                </Link>
                                <Button variant="outline" className="w-full">
                                    Logout
                                </Button>
                            </div>
                        </SheetContent>
                    </Sheet>
                </nav>
            </header >

        </>
    );

}
export default MenuPrincipal;