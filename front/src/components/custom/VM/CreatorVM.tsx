
import { Card, CardHeader, CardTitle, CardDescription, CardContent, CardFooter } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { createVM } from "@/services/toolVM"
import { getResourcesAll } from "@/services/hostMetrics";
import { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";
import { AlertError, Loading } from "@/components/Alerts/Alerts";
import Swal from "sweetalert2";
interface MyToken {
    id: string;
}


export default function CreatorVM() {
    const [resourses, setResourses] = useState<any>([]);
    const token = localStorage.getItem('token');

    const userId = (jwtDecode(token) as MyToken);

    useEffect(() => {
        getResourcesAll(localStorage.getItem('token'))
            .then(data => {
                setResourses(data);
            })
            .catch(error => {
                console.error(error);
            });

    }, []);
    const handleSelectPlan = async (resourceId: string) => {
        try {
            Loading();
            const data = {
                idVM: 2,
                idUser: userId.id,
                idRsc: resourceId
            };
            const response = await createVM(token, data);
            Swal.close();
            window.location.href = "/machineManagement";
            console.log(response);
        } catch (error) {
            AlertError("", "Error al crear la maquina virtual");
            Swal.close();
            console.error(error);
        }
    }

    return (

        <div className="container grid gap-8 pt-4 pb-6 px-4 md:px-6">
            <div className="flex flex-col items-center justify-center space-y-4 text-center">
                <div className="space-y-2">
                    <h2 className="text-3xl font-bold tracking-tighter sm:text-5xl">Planes de Creacion de Maquinas</h2>
                    <p className="max-w-[700px] text-gray-500 md:text-xl/relaxed lg:text-base/relaxed xl:text-xl/relaxed dark:text-gray-400">
                        Escoge el plan que mejor se adapte a tus necesidades y comienza a crear tus maquinas virtuales.
                    </p>
                </div>
            </div>
            <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
                <Card className="border-2 border-gray-200 dark:border-gray-800">
                    <CardHeader>
                        <CardTitle>Estandar</CardTitle>
                        <CardDescription>Perfecta para el uso personal</CardDescription>
                    </CardHeader>
                    <CardContent className="space-y-4">
                        <div className="flex items-baseline gap-2">
                            <span className="text-4xl font-bold">${resourses[1] && resourses[1].price_hourly}</span>
                            <span className="text-gray-500 dark:text-gray-400">/hour</span>
                        </div>
                        <ul className="space-y-2 text-gray-500 dark:text-gray-400">
                            <li className="flex items-center gap-2">
                                <CheckIcon className="h-5 w-5 text-green-500" />
                                {resourses[1] && resourses[1].cpu}vCPU
                            </li>
                            <li className="flex items-center gap-2">
                                <CheckIcon className="h-5 w-5 text-green-500" />
                                {resourses[1] && resourses[1].ram} MB RAM
                            </li>
                            <li className="flex items-center gap-2">
                                <CheckIcon className="h-5 w-5 text-green-500" />
                                {resourses[1] && resourses[1].disk} MB SSD
                            </li>
                        </ul>
                    </CardContent>
                    <CardFooter>
                        <Button className="w-full" onClick={() => handleSelectPlan(resourses[0].id)} >Selecione el Plan</Button>
                    </CardFooter>
                </Card>
                <Card className="border-2 border-gray-200 dark:border-gray-800 ring-2 ring-primary dark:ring-primary ">
                    <CardHeader>
                        <CardTitle>Professional</CardTitle>
                        <CardDescription>Ideal para pequeñas y medianas empresas.</CardDescription>
                    </CardHeader>
                    <CardContent className="space-y-4">
                        <div className="flex items-baseline gap-2">
                            <span className="text-4xl font-bold">${resourses[0] && resourses[0].price_hourly}</span>
                            <span className="text-gray-500 dark:text-gray-400">/hour</span>
                        </div>
                        <ul className="space-y-2 text-gray-500 dark:text-gray-400">
                            <li className="flex items-center gap-2">
                                <CheckIcon className="h-5 w-5 text-green-500" />
                                {resourses[0] && resourses[0].cpu} vCPU
                            </li>
                            <li className="flex items-center gap-2">
                                <CheckIcon className="h-5 w-5 text-green-500" />
                                {resourses[0] && resourses[0].ram} MB RAM
                            </li>
                            <li className="flex items-center gap-2">
                                <CheckIcon className="h-5 w-5 text-green-500" />
                                {resourses[0] && resourses[0].disk} MB SSD
                            </li>

                        </ul>
                    </CardContent>
                    <CardFooter>
                        <Button className="w-full" onClick={() => handleSelectPlan(resourses[1].id)}>selecione el Plan</Button>
                    </CardFooter>
                </Card>
                <Card className="border-2 border-gray-200 dark:border-gray-800">
                    <CardHeader>
                        <CardTitle>Empresas</CardTitle>
                        <CardDescription>Solución escalable para empresas de gran escala.</CardDescription>
                    </CardHeader>
                    <CardContent className="space-y-4">
                        <div className="flex items-baseline gap-2">
                            <span className="text-4xl font-bold">${resourses[2] && resourses[2].price_hourly}</span>
                            <span className="text-gray-500 dark:text-gray-400">/month</span>
                        </div>
                        <ul className="space-y-2 text-gray-500 dark:text-gray-400">
                            <li className="flex items-center gap-2">
                                <CheckIcon className="h-5 w-5 text-green-500" />
                                {resourses[2] && resourses[2].cpu} vCPU
                            </li>
                            <li className="flex items-center gap-2">
                                <CheckIcon className="h-5 w-5 text-green-500" />
                                {resourses[2] && resourses[2].ram} MB RAM
                            </li>
                            <li className="flex items-center gap-2">
                                <CheckIcon className="h-5 w-5 text-green-500" />
                                {resourses[2] && resourses[2].disk} MB SSD
                            </li>
                        </ul>
                    </CardContent>
                    <CardFooter>
                        <Button className="w-full" onClick={() => handleSelectPlan(resourses[2].id)}>Select Plan</Button>
                    </CardFooter>
                </Card>
            </div>
        </div>

    )
}

function CheckIcon(props) {
    return (
        <svg
            {...props}
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
        >
            <path d="M20 6 9 17l-5-5" />
        </svg>
    )
}