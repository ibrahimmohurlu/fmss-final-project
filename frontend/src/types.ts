export type LoginResponse = { status: "success" | "erro", message: string }
export type ServerLoginResponse = {
    id: number,
    email: string,
    password: string,
    created_at: string,
    updated_at: string
}
export type AuthSession = { authenticated: false, user: undefined } | { authenticated: true, user: { email: string } }

export type Package = {
    id: number,
    name: string,
    description: string,
    price: number,
    durationDays: number,
    listingAllowance: number
}

export type UserPackage = {
    id: number,
    package_id: number,
    purchase_date: string,
    expiration_date: string,
    is_confirmed: boolean,
    remaining_listing_allowance: number
}
export type UserListing = {
    id: number,
    title: string,
    description: string,
    price: number,
    status: "IN_REVIEW" | "ACTIVE" | "INACTIVE",
    user_id: number,
    created_at: string,
    updated_at: string
}