import { useForm, SubmitHandler } from "react-hook-form";
import { AlertError, AlertSuccess } from "@/components/Alerts/Alerts";
import axios from "axios";

type Inputs = {
    name: string,
    password: string,
    confrimPass: string,
    email: string,
};
function Register() {
    const { register, handleSubmit, formState: { errors } } = useForm<Inputs>();
    const onSubmit: SubmitHandler<Inputs> = data => {
        if (data.confrimPass !== data.password) {
            AlertError("Error", "Las contraseñas no coinciden");
        }
        const emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
        if (!emailRegex.test(data.email)) {
            AlertError("Error", "Correo no valido");
        }
        axios.post("http://localhost:8080/users/signup", {
            name: data.name,
            email: data.email,
            password: data.password
        }).then((res) => {
            console.log(res);
            AlertSuccess("Exito", "Usuario registrado correctamente");
        }).catch((err) => {
            console.log(err);
            AlertError("Error", "Error al registrar usuario");
        })

    };

    return (
        <div className="w-full h-screen  flex justify-center items-center bg-[url(/datacenter.jpg)] bg-cover bg-center" >
            <div className=" pt-6">
                <div className="lg:flex flex-row lg:w-[760px] pt-6 pb-6 px-8 gap-3 rounded-lg bg-[#050505d3]">
                    <div className="lg:w-[360px] lg:rounded-lg bg-[url(https://i.pinimg.com/originals/a4/15/54/a415545bb9a81e35d09b2fc184e2e801.gif)] bg-cover bg-center ">


                    </div>
                    <form onSubmit={handleSubmit(onSubmit)} className="lg:w-[380px]  lg:rounded-md px-8 space-y-4 pt-2 pb-4 bg-[#050505d3]">
                        <h1 className="text-center text-lg text-white"><strong>Registro</strong></h1>
                        <div className="w-full">
                            <label className="text-white">Usuario</label>
                            <input  {...register("name", { required: true })} className="w-full border border-slate-600 rounded-md p-1 pl-2" placeholder="Example Name" />
                            {errors.name && <span className="text-red-500">Campo requerido</span>}
                        </div>
                        <div className="w-full">
                            <label className="text-white">Correo</label>
                            <input {...register("email", { required: true })} className="w-full border border-slate-600 rounded-md p-1 pl-2" placeholder="example@mail.com" />
                            {errors.email && <span className="text-red-500">Campo requerido</span>}
                        </div>
                        <div className="w-full">
                            <label className="text-white">Contraseña</label>
                            <input {...register("password", { required: true })} className="w-full border border-slate-600 rounded-md p-1 pl-2" type="password" placeholder="********" />
                            {errors.password && <span className="text-red-500">Campo requerido</span>}
                        </div>
                        <div className="w-full">
                            <label className="text-white">Confrimar Contraseña</label>
                            <input {...register("confrimPass", { required: true })} className="w-full border border-slate-600 rounded-md p-1 pl-2" type="password" placeholder="**********" />
                            {errors.confrimPass && <span className="text-red-500">Campo requerido</span>}
                        </div>
                        <div className="w-full flex flex-row justify-center items-center gap-8">
                            <button type="submit" className="wfull bg-blue-500 text-white h-[35px] w-[130px] rounded-md hover:bg-blue-700">Registrar</button>
                            <button className="bg-red-500 text-white h-[35px] w-[130px] rounded-md hover:bg-red-700" ><a href="/">Cancelar</a></button>
                        </div>

                    </form>
                </div>
            </div>

        </div>
    )

}
export default Register;