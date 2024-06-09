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