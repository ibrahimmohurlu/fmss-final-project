import { UserListing } from "@/types";
import { revalidateTag } from "next/cache";

export async function fetchUserListings(email: string, password: string) {

    const authHeader = `Basic ${new Buffer(email + ':' + password).toString('base64')}`;
    const res = await fetch("http://localhost:8080/api/v1/users/listings", { next: { tags: ["listings"] }, headers: { "Authorization": authHeader } });
    const data = await res.json() as UserListing[];
    return data;
}

export async function fetchListings() {
    const res = await fetch("http://localhost:8080/api/v1/listings", { next: { tags: ["listings"] } });
    const data = await res.json() as UserListing[];
    return data;
}


export async function toggleListingStatus(email: string, password: string, id: number) {
    "use server"
    const authHeader = `Basic ${new Buffer(email + ':' + password).toString('base64')}`;
    const res = await fetch(`http://localhost:8080/api/v1/listings/${id}/status/toggle`, { method: "PATCH", headers: { "Authorization": authHeader } });
    revalidateTag("listings")
}
