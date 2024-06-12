import Swal from 'sweetalert2';

export function AlertError(title: string, text: string) {
    Swal.fire({
        icon: 'error',
        title: title,
        text: text,
    })
}
export function AlertSuccess(title: string, text: string) {
    Swal.fire({
        icon: 'success',
        title: title,
        text: text,
    })
}

export function AlertInfo(title: string, text: string) {
    Swal.fire({
        icon: 'info',
        title: title,
        text: text,
    })
}
export function Loading() {
    Swal.fire({
        title: 'Cargando',
        allowOutsideClick: false,
        showConfirmButton: false,
        willOpen: () => {
            Swal.showLoading();
        }
    });
}