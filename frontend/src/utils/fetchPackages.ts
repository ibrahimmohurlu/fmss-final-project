export default async function fetchPackages() {
    const res = await fetch("http://localhost:8080/api/v1/packages");
    const data = await res.json() as Package[];
    return data;
}