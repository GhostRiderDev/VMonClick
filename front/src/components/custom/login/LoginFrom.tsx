import { useForm, SubmitHandler } from "react-hook-form";
type Inputs = {
    user: string,
    pass: string,
};
function LoginFrom() {
    const { register, handleSubmit, formState: { errors } } = useForm<Inputs>();
    const onSubmit: SubmitHandler<Inputs> = data => console.log(data);

    return (
        <div className="w-full h-screen  flex justify-center items-center bg-[url(/datacenter.jpg)] bg-cover bg-center" >
            <div className=" pt-6">
                <div className="lg:flex flex-row lg:w-[760px] pt-6 pb-6 px-8 gap-3 rounded-lg bg-[#050505d3]">
                    <div className="lg:w-[360px] lg:rounded-lg bg-[url(https://i.pinimg.com/originals/a4/15/54/a415545bb9a81e35d09b2fc184e2e801.gif)] bg-cover bg-center ">


                    </div>
                    <form onSubmit={handleSubmit(onSubmit)} className="lg:w-[380px]  lg:rounded-md px-8 space-y-4 pt-2 pb-4 bg-[#050505d3]">
                        <h1 className="text-center text-lg text-white"><strong>Login</strong></h1>
                        <div className="w-full">
                            <label className="text-white">Usuario</label>
                            <input  {...register("user", { required: true })} className="w-full border border-slate-600 rounded-md p-1 pl-2" />
                            {errors.user && <span className="text-red-500">Campo requerido</span>}
                        </div>
                        <div className="w-full">
                            <label className="text-white">Contraseña</label>
                            <input {...register("pass", { required: true })} className="w-full border border-slate-600 rounded-md p-1 pl-2" type="password" />
                            {errors.pass && <span className="text-red-500">Campo requerido</span>}
                        </div>
                        <div className="w-full flex flex-col justify-center items-center space-y-3  ">
                            <button type="submit" className="w-full bg-blue-500 text-white h-[35px] rounded-md">Ingresar</button>
                            <a href="/register" className="text-slate-300 text-center hover:text-slate-200">Olvidaste tu contraseña?</a>
                        </div>

                    </form>
                </div>
            </div>

        </div>
    )

}
export default LoginFrom;