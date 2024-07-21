type LoginResponse = { status: "success" | "error", message: string }
type ServerLoginResponse = {
    id: number,
    email: string,
    password: string,
    created_at: string,
    updated_at: string
}
type AuthSession = { authenticated: false, user: undefined } | { authenticated: true, user: { email: string } }

type Package = {
    id: number,
    name: string,
    description: string,
    price: number,
    durationDays: number,
    listingAllowance: number
}