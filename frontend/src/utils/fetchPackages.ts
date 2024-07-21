import { UserPackage, Package } from "@/types";

export async function fetchPackages() {
    const res = await fetch("http://localhost:8080/api/v1/packages");
    const data = await res.json() as Package[];
    return data;
}

export async function fetchUserPackages(email: string, password: string) {
    const authHeader = `Basic ${new Buffer(email + ':' + password).toString('base64')}`;
    const res = await fetch("http://localhost:8080/api/v1/users/packages", { headers: { "Authorization": authHeader } });
    const data = await res.json() as UserPackage[];
    return data;
}