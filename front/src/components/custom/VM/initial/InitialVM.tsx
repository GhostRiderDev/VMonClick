import { Card, CardHeader, CardContent, CardFooter } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { deleteInstance, getInstances } from "@/services/toolVM";
import { RiDeleteBin6Line } from "react-icons/ri";

const InitialVM = () => {
    const [machines, setMachines] = useState<any>([]);
    const token = localStorage.getItem('token');
    useEffect(() => {
        getInstances(localStorage.getItem('token'))
            .then(data => {
                setMachines(data);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);
    const handleDelete = async (id: string) => {
        deleteInstance(token, id)
            .then(data => {
                console.log(data);
                // Actualizar el estado de 'machines' para eliminar la instancia
                setMachines(machines.filter(machine => machine.id !== id));
            })
            .catch(error => {
                console.error(error);
            });
    }


    return (
        <>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 p-4 md:p-6">
                {machines.filter(machine => !machine.finish).map((machine, index) => (
                    <Card key={machine.id} className="relative group overflow-hidden rounded-lg shadow-lg hover:shadow-xl transition-transform duration-300 ease-in-out hover:-translate-y-2">
                        <CardHeader className="bg-gray-100 dark:bg-gray-800 p-4 flex items-center justify-between">
                            <div className="absolute right-0 top-0 m-2">
                                <Button className="hover:bg-red-500" variant="outline" size="sm" onClick={() => handleDelete(machine.id)}>
                                    <RiDeleteBin6Line />
                                </Button>
                            </div>
                            <div className=" flex flex-row">
                                <div className="font-semibold text-center">VM {index + 1}</div>
                            </div>
                            <div className="absolute left-0 top-0 m-2">
                                <img
                                    src="https://cdn4.iconfinder.com/data/icons/logos-and-brands/512/348_Ubuntu_logo-512.png"
                                    alt="Ubuntu Logo"
                                    style={{ width: '60px', height: '60px' }}
                                />

                            </div>
                            <div
                                className={`px-2 py-1 rounded-full text-xs font-medium ${machine.stop ? 'bg-red-100 text-red-600 dark:bg-red-900 dark:text-red-300' : 'bg-green-100 text-green-600 dark:bg-green-900 dark:text-green-300'}`}
                            >
                                {machine.stop ? 'Stopped' : 'Running'}
                            </div>
                        </CardHeader>
                        <CardContent className="p-4 space-y-2">
                            <div className="flex items-center justify-between">
                                <div className="text-gray-500 dark:text-gray-400">CPU</div>
                                <div>50%</div>
                            </div>
                            <div className="flex items-center justify-between">
                                <div className="text-gray-500 dark:text-gray-400">Memory</div>
                                <div>4GB</div>
                            </div>
                            <div className="flex items-center justify-between">
                                <div className="text-gray-500 dark:text-gray-400">Disk</div>
                                <div>100MB</div>
                            </div>
                        </CardContent>
                        <CardFooter className="bg-gray-100 dark:bg-gray-800 p-4 flex items-center justify-between">
                            <Button variant="outline" size="sm" className={`${machines.stop ? 'hover:bg-red-500' : 'hover:bg-green-500 hover:p-4'}`}>
                                {machine.stop ? 'Run' : 'Stop'}
                            </Button>
                            <Link
                                to="#"
                                className="text-gray-500 hover:text-gray-900 hover:bg-sky-500 hover:rounded-md hover:p-2 dark:text-gray-400 dark:hover:text-gray-50"

                            >
                                Ver Metricas
                            </Link>

                        </CardFooter>
                    </Card>
                ))}
            </div>
        </>
    );
}
export default InitialVM;
