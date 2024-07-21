export default async function fetchUser(email: string, password: string) {
    const authHeader = `Basic ${new Buffer(email + ':' + password).toString('base64')}`;
    const response = await fetch(`http://localhost:8080/api/v1/users/${email}`, {
        method: "GET",
        headers: {
            "Authorization": authHeader
        }
    });
    const result = await response.json()
    return result;
}