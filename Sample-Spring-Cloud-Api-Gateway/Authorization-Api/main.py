import uvicorn
from fastapi import FastAPI

api: FastAPI = FastAPI()


@api.get("/greeting")
async def greeting() -> dict[str]:
    return dict(Say='greeting, I\'m Authorization API!')


@api.get("/users/{user_id}")
async def greeting(user_id: int) -> dict[str]:
    return dict(Say=f'Hello sample user! user id = {user_id}')


if __name__ == '__main__':
    uvicorn.run(app=api, port=9090, host="0.0.0.0")
