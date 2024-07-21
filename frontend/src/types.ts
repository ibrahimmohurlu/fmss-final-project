type LoginResponse = { status: "success" | "error", message: string }
type ServerLoginResponse = {
    id: number,
    email: string,
    password: string,
    created_at: string,
    updated_at: string
}