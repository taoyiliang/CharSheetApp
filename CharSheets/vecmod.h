#ifndef VECMOD_H
#define VECMOD_H

template<class T>
std::ostream& operator<<(std::ostream& stream, const std::vector<T>& content)
{
    if (content.size() > 0)
    {
        stream << content[0];

        for (std::size_t i = 1; i < content.size(); ++i)
            stream << ", " << content[i];
    }

    return stream;
}

#endif // VECMOD_H
